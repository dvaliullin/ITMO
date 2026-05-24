        ORG     0x70
ADDR:   WORD    $STR
NUL:    WORD    0x00
BUFF:   WORD    0x00
START:  CLA
S1:     IN      3
        AND     #0x40
        BEQ     S1
        LD      (ADDR)+
        ST      BUFF
        SWAB
        SXTB
        CMP     NUL
        BEQ     STOP
        OUT     0xC
S2:     IN      3
        AND     #0x40
        BEQ     S2
        LD      BUFF
        SXTB
        CMP     NUL
        BEQ     STOP
        OUT     0xC
        JUMP    S1
STOP:   HLT
        ORG     0x5C1
STR:    WORD    0xEEEF
        WORD    0xFEF8
        WORD    0x2100