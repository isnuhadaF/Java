# `data/models/`

This package is intended for models used by the data-access layer. Depending
on the storage design, a model may represent a persisted record, a mapped
document, or another data structure exchanged with a repository.

## Role and boundaries

Keep a model's fields and constraints meaningful for the data it represents.
Repositories commonly map between stored values and these objects. Do not
shape a persistence representation solely to match one API response; that
couples the storage schema to a client-facing contract.

“Model” is an overloaded term in software architecture. The presence of a
class here does not make it a domain entity or establish that domain logic
belongs in this package. If a future design introduces a distinct domain
model, define how it maps to persistence models rather than silently treating
the two as interchangeable. Request and response DTOs are documented under
[`dtos/`](../../dtos/README.md).
