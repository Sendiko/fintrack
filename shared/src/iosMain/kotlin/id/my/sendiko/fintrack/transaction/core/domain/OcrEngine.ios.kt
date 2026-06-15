package id.my.sendiko.fintrack.transaction.core.domain

import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.Foundation.NSData
import platform.Foundation.create
import platform.Vision.VNImageRequestHandler
import platform.Vision.VNRecognizeTextRequest
import platform.Vision.VNRecognizedTextObservation
import platform.Vision.VNRequestTextRecognitionLevelAccurate
import kotlin.coroutines.resume

actual class OcrEngine actual constructor() {
    @OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
    actual suspend fun extractTextFromImage(imageBytes: ByteArray): String =
        suspendCancellableCoroutine { continuation ->
            // 1. Map Kotlin ByteArray directly into Apple's native NSData container safely
            val nsData = imageBytes.usePinned { pinned ->
                NSData.create(bytes = pinned.addressOf(0), length = imageBytes.size.toULong())
            }

            // 2. Set up the Vision text recognition request
            val request = VNRecognizeTextRequest { request, error ->
                if (error != null) {
                    continuation.resume("")
                    return@VNRecognizeTextRequest
                }
                val results = request?.results ?: emptyList<Any>()
                val parsedText = results.joinToString(separator = "\n") {
                    (it as VNRecognizedTextObservation).topCandidates(1u).first().toString()
                }
                continuation.resume(parsedText)
            }

            request.recognitionLevel = VNRequestTextRecognitionLevelAccurate

            // 3. FIX: Feed NSData directly into the handler instead of dealing with CGImage pointers!
            // We pass emptyMap() for options. Kotlin/Native expects a Map matching Map<Any?, *>?
            val handler = VNImageRequestHandler(
                data = nsData,
                options = emptyMap<Any?, Any?>() // Use Any? instead of Any*
            )
            try {
                handler.performRequests(listOf(request), null)
            } catch (e: Exception) {
                continuation.resume("")
            }
        }
}