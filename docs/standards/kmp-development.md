# KMP Development Standard

## Test naming (Kotlin/Native compatibility)

- **Constraint**: Do not use parentheses `()` or other special characters besides spaces and underscores in backticked test function names.
- **Reason**: Native targets (iOS, Linux) fail to compile tests with these characters in their symbol names.
