ORG 0x0B2
START:
    CLA
    ST R
    LD Z
    PUSH
    CALL $func
    POP
    SUB R
    ST R
    LD Y
    PUSH
    CALL $func
    POP
    DEC
    SUB R
    ST R
    LD X
    INC
    PUSH
    CALL $func
    POP
    INC
    SUB R
    ST R
    HLT
Z: WORD 0x021F
Y: WORD 0xFE5A
X: WORD 0x0000
R: WORD 0xFA88

ORG 0x736
func:
    LD &1
    BPL return_A
    SUB A
    BMI return_A
    BEQ return_A
    ADD A
    ASL
    ASL
    ADD &1
    ADD B
    JUMP return
    return_A: LD A
    return: ST &1
    RET
A: WORD 0xFA88
B: WORD 0x00A2