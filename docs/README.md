# How This Documentation Works

Everything about this project lives in one of a few kinds of place.

**The issue tracker owns work**: stories, bugs, ideas, anything with a status that will someday be "done". If it tracks progress, it is an issue on GitHub, never a document.

**The knowledge base owns knowledge**, in `docs/knowledge/`: decisions, specifications, research and guides, anything a person would look up. Each section folder's `README.md` says what belongs in it.

- To add a document, create a `.md` file in the right section folder. It opens with a `# Title` heading (the title alone, no type prefix), then an identifier line and a keywords line, as the existing documents do.
- Identifiers are per record type: `ADR-` for decisions, `RAD-` for research, and `DOC-` for specifications and guides, one sequence across both. Filenames start with the identifier, so a document can be cited and keeps its name when its title changes.
- The local tree owns the layout. Reorganize by moving files.
- The knowledge base is planned to mirror to the repository wiki. Until the wiki is set up for it, `docs/knowledge/` is plain git and is not published anywhere else.

**Plain git owns the machinery**: `AGENTS.md` at the repo root, this file and `docs/standards/` for agents, `docs/outbox/` for artifacts written for people outside the project, and `docs/_archive/` for retired files. None of it is part of the knowledge base.

Git is the version history for all of it. Diffs, blame and merges work on documents exactly as they do on code.
