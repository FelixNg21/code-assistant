package com.github.felixng21.codeassistant.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages

class ShowSuggestionsAction: AnAction() {
    override fun actionPerformed(p0: AnActionEvent) {
        val suggestions = getSuggestionsFromBackend()

        Messages.showMessageDialog(
            p0.project,
            "Suggestions: ${suggestions.joinToString(", ")}",
            "Code Suggestions",
            Messages.getInformationIcon()
        )
    }

    private fun getSuggestionsFromBackend(): List<String> {
        // Call the backend to get suggestions
        return listOf("suggestion1", "suggestion2")
    }
}