ORG 0x0D0
START:
    LD A
    PUSH
    LD B
    PUSH
    LD C
    PUSH
    LD D
    PUSH
    CALL $func
    POP
    ST R
    HLT
A: WORD 0x0002
B: WORD 0x0004
C: WORD 0x0006
D: WORD 0x0008
R: WORD 0xFA88

ORG 0x746
func:
    LD &4
    ADD &3
    ADD &2
    ADD &1
    ASR
    ASR
    ST &1
    RET