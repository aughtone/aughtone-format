# 🪄 Magic Prompt: Bootstrap AI Skill Publishing

Use this prompt to initialize the machine-readable "AI Skill" standard in any other library. Copy and paste the block below into your AI assistant (e.g., Gemini, ChatGPT, Claude) when working in the target repository.

---

### **The Meta-Prompt**

**Role:** You are a Senior Staff Engineer specializing in Developer Experience (DX) and AI-Assisted Development.
**Task:** Analyze this repository and initialize the "AI Skill Publishing" and "Agent Governance" standards.

**1. Identity & Standardized Naming**
- Determine Maven `group` and `artifact` from build files.
- Path: `src/commonMain/resources/META-INF/ai-skills/[group].[artifact].ai-skill.md`

**2. Create AI-Skill Definition**
Generate the file with YAML frontmatter (`skill-id`, `spec-version: "1.0"`, `type: "Aughtone AI-Skill"`) and sections for the **AI Toolbox**, **Compliance**, **Immutability**, and **🤖 Usage Rules**.

**3. Create Agent Onboarding Guide (AGENTS.md)**
Generate a guide in the root for repository contributors covering:
- Documentation Hierarchy (e.g., 5-sector hierarchy).
- Core Principles (TDD, Immutability-first).
- Interaction Rules (Plan-first, mandatory approval).
- Linkage to the Skill file created in step 2.

**4. Update README.md**
Append the `## 🤖 AI-Assisted Development` section to the end of the root `README.md`:

```markdown
## 🤖 AI-Assisted Development
This library includes embedded, machine-readable "skills" to help AI assistants understand its APIs and best practices.

- **Discovery**: Look for `META-INF/ai-skills/*.ai-skill.md`

### 🪄 Magic Prompt for AI Assistants
If you are using an AI assistant (like Claude, Gemini, or ChatGPT) to write code with this library, paste this prompt first:

> "Scan all project dependencies for AI Skill files in `META-INF/ai-skills/` with the prefix `[library_maven_group]`. Use these to understand the API patterns, types, and governance for this library. If they are not found in the local classpath, refer to [Public Repository URL] for the source definitions."
```
