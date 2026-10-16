# `utils/`

This package is reserved for small, broadly reusable support code that does
not have a more accurate home in a particular layer. Examples might include
pure formatting or conversion helpers used in several packages.

“Utility” should not become a category for code whose responsibility is
unclear. A helper used by one layer usually belongs near that layer's
responsibility. Application policy belongs with the relevant use case or
model; storage operations belong behind repositories; transport translation
belongs at an adapter.

Prefer cohesive, deterministic helpers with explicit inputs and outputs.
Avoid hidden mutable state, broad grab-bag classes, and dependencies on
application-wide services that make a helper difficult to understand or
test.
