# `src/`

This is the production Java source root configured in `noWayToHide.iml`. Java
package folders below it form the application namespace; they organize
classes by role and make those roles visible to readers.

## What belongs here

Place production Java source in a package under `com/lastma/`. The package
structure separates request handling, use-case coordination, data access,
and supporting types. Keep source files in the package that owns their
responsibility rather than adding unrelated classes directly to this root.

## What does not belong here

Tests have their own configured source root at [`test/`](../test/README.md).
IDE settings belong in [`.idea/`](../.idea/README.md). Keeping these roots
separate lets tools and developers distinguish production code from tests and
project configuration.

See [`com/`](com/README.md) for the namespace and
[`com/lastma/`](com/lastma/README.md) for the application package guide.
