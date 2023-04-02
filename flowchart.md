---
Markeplace
---
This flowchart outlines the logic for our code.
```mermaid
flowchart TD
    A[Start]-->B{Login Menu}
    B-->|login| C{check for username and password}
    B-->|create new account| D{check for username and password}
    B-->|quit| E[End]
```
