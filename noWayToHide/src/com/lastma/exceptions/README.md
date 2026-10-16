# `exceptions/`

This package is intended for exception types that give application failures
clear meaning, such as a requested resource not being found or an operation
being invalid under current application rules. An exception communicates an
abnormal outcome through the call stack; it is not itself an HTTP status or
user-facing message.

## Handling errors across layers

Use an exception type when callers need to distinguish a failure and recover
or translate it. Catch an exception only where the code can add useful
context, recover, or convert it into the boundary's error contract. An
inbound adapter may translate an application failure into a protocol-specific
response, but the application exception should not depend on that protocol.

Do not use exceptions for ordinary branching when a normal result type or
conditional is clearer. Do not swallow failures or return an empty,
success-shaped fallback that hides an error. Keep messages safe for their
audience and avoid exposing sensitive implementation details.
