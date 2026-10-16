# `dtos/`

A **Data Transfer Object (DTO)** is a data-carrying object used to transfer a
defined set of values across a boundary. In this project, the package
separates inbound [`requests/`](requests/README.md) from outbound
[`responses/`](responses/README.md).

DTOs make a boundary's data shape explicit and can prevent internal
representations from becoming accidental external contracts. They are not
automatically required for every method call: use them where the explicit
boundary is valuable, rather than creating redundant wrapper types without a
clear purpose.

DTOs generally carry data; validation, mapping, and use-case behavior are
performed by the appropriate boundary or application component. Their fields
may resemble those of a data model, but similarity does not make them the
same abstraction. Map between representations when they have different
owners, lifecycles, or change reasons. Do not put persistence operations or
multi-step business workflows in a DTO.
