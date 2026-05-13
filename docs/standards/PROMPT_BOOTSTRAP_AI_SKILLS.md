# 🪄 Magic Prompt: Bootstrap AI Skill Publishing

Use this prompt to initialize the machine-readable "AI Skill" standard in any other library. Copy and paste the block below into your AI assistant (e.g., Gemini, ChatGPT, Claude) when working in the target repository.

---

### **Prompt: Bootstrap AI Skill Publishing**

**Role:** Act as a Senior Software Architect and Technical Writer.
**Task:** Initialize the "AI Skill Publishing" standard in this repository to make it compatible with AI-assisted development.

**Instructions:**

1.  **Identify Identity**: Determine the Maven `group` and `artifact` ID from the build configuration (e.g., `build.gradle.kts` or `gradle.properties`).
2.  **Create Skill File**: 
    - Create the file at `src/commonMain/resources/META-INF/ai-skills/[group].[artifact].ai-skill.md`.
    - Add YAML frontmatter:
      ```yaml
      ---
      skill-id: [group].[artifact]
      spec-version: 1.0
      scope: core
      compatibility: ">=[current_version]"
      ---
      ```
3.  **Generate Content**: Analyze the codebase and create sections for:
    - **The AI Toolbox**: Categorized index of primary APIs and their "Preference" (when to use them).
    - **Compliance & Standards**: List of ISO/RFC/Mathematical standards used.
    - **Serialization & Immutability**: Rules for data handling.
    - **## 🤖 Agent Onboarding**: Mandatory usage rules for AI agents.
4.  **Update README.md**: Add the following section at the end of the file:
    ```markdown
    ## 🤖 AI-Assisted Development
    This library includes embedded, machine-readable "skills" to enhance the experience of developers using AI code assistants.

    - **AI Skill Discovery**: Look for `META-INF/ai-skills/*.ai-skill.md`

    ### 🪄 Magic Prompt for AI Assistants
    Use this prompt to inform your AI assistant about the standards and patterns embedded in this library:

    > "Scan all project dependencies for AI Skill files located in `META-INF/ai-skills/` with the prefix `[group]`. Use these files to understand the coding standards, API patterns, and governance rules for the library. If you cannot find them in the dependencies, refer to the [Public Repository URL] for the source definitions."
    ```
5.  **Governance Update**: If an `AGENTS.md` exists, add a reference to the new skill file to ensure immediate agent awareness.

---
