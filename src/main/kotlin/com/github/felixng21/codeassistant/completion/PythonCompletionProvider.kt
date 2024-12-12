package com.github.felixng21.codeassistant.completion

import com.intellij.codeInsight.inline.completion.*
import com.intellij.openapi.diagnostic.thisLogger
import java.util.concurrent.atomic.AtomicReference
import okhttp3.sse.EventSource

class PythonCompletionProvider : InlineCompletionProvider() {

    companion object{
        private val logger = thisLogger()
    }

    private val currentCallRef = AtomicReference<EventSource?>(null)

    override val id: InlineCompletionProviderID
        get() = InlineCompletionProviderID("CodeGPTInlineCompletionProvider")

    override val suggestionUpdateManager: CodeCompletionSuggestionUpdateAdapter
        get() = CodeCompletionSuggestionUpdateAdapter()

    override val insertHandler: InlineCompletionInsertHandler
        get() = CodeCompletionInsertHandler()

    override val providerPresentation: InlineCompletionProviderPresentation
        get() = CodeCompletionProviderPresentation()

    override suspend fun getSuggestionDebounced(request: InlineCompletionRequest): InlineCompletionSuggestion {
        return if (service<ConfigurationSettings>().state.codeCompletionSettings.multiLineEnabled) {
            getMultiLineSuggestionDebounced(request)
        } else {
            getSingleLineSuggestionDebounced(request)
        }
    }
}