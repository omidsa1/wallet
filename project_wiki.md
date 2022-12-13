### Technical Decisions Made
The types of relationships between entities and the requirement for intra-entity data retrieval enforces the need for a relational database. We have opted for MySQL, yet due to project's lack of reliance on DB specific features, it could be easily migrated to any other relational database.

Tests are not written for the project as of now, the reason being the volatile properties of structure and layers until completion (we have not taken a TDD approach, so tests were not designed in an iterative fashion).

