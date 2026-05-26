        ORG     0x0             ; Инициализация векторов прерывания
V0:     WORD    $DEFAULT, 0x180 ; Вектор прерывания #0
V1:     WORD    $INT1, 0x180    ; Вектор прерывания #1
V2:     WORD    $DEFAULT, 0x180 ; Вектор прерывания #2
V3:     WORD    $INT3, 0x180    ; Вектор прерывания #3
V4:     WORD    $DEFAULT, 0x180 ; Вектор прерывания #4
V5:     WORD    $DEFAULT, 0x180 ; Вектор прерывания #5
V6:     WORD    $DEFAULT, 0x180 ; Вектор прерывания #6
V7:     WORD    $DEFAULT, 0x180 ; Вектор прерывания #7

DEFAULT:IRET                    ; Обработка прерывания по умолчанию

        ORG     0x12
X:      WORD    ?
MAX:    WORD    0x29 ; 41
MIN:    WORD    0xFFD4 ; -44

START:  DI                      ; Загрузка начальных векторов прерывания
        CLA                     ; Запрет прерываний для неиспользуемых ВУ
        OUT     1
        OUT     5
        LD      #9              ; Разрешить прерывания на вектор 1
        OUT     3               ; (1000|0001=1001) в MR КВУ-1
        LD      #0xB            ; Разрешить прерывания на вектор 3
        OUT     7               ; (1000|0011=1011) в MR КВУ-3
        OUT     0xB
        OUT     0xD
        OUT     0x11
        OUT     0x15
        OUT     0x19
        OUT     0x1D
        EI
        
PROG:   DI                      ; Основная программа
        LD      X
        ADD     #2
        CALL    CHECK
        ST      X
        EI
        JUMP    PROG

CHECK:  CMP     MIN             ; Проверка X на принадлежность к ОДЗ
        BMI     RET_MIN
        CMP     MAX
        BMI     RETURN
RET_MIN:LD      MIN
RETURN: RET

INT1:   DI                      ; Обработка прерывания на ВУ-1
        LD      X
        NOP
        ASL
        ADD     X
        ADD     #4
        OUT     2
        NOP
        EI
        IRET

INT3:   DI                      ; Обработка прерывания на ВУ-3
        IN      6
        NOP
        OR      X
        ST      X
        NOP
        EI
        IRET




        