# `dtos/responses/`

Response DTOs describe data returned across an outbound boundary. A controller
or other adapter can use one to represent the result of a service operation
without exposing a persistence-specific representation by default.

## Design guidance

Include values that the caller is permitted and expected to receive. The
response shape is part of the boundary contract, so consider compatibility
when changing it. Avoid returning database records wholesale or exposing
implementation details that callers should not depend on.

Create response objects from application results at the boundary where the
external shape is known. A response DTO is a data representation, not the
place to run storage operations or coordinate a use case. A DTO can resemble
an internal model, but keep separate types when their ownership or change
requirements differ.
