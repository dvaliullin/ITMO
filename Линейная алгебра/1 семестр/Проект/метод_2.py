import numpy as np

def mixed_product(u, v, w):
    """Вычисляет смешанное произведение трех векторов"""
    return np.dot(u, np.cross(v, w))

def point_position(P, A, B, C, D):
    
    P = np.array(P)
    A = np.array(A)
    B = np.array(B)
    C = np.array(C)
    D = np.array(D)
    
    AB = B - A
    AC = C - A
    AD = D - A
    BC = C - B
    BD = D - B
    CD = D - C
    AP = P - A
    BA = A - B
    BP = P - B
    
    positions = []
    zero_positions = []  
    
    # 1. Грань ABC (противоположная D)
    M1 = mixed_product(AB, AC, AD)
    M2 = mixed_product(AB, AC, AP)
    pos = M1 * M2
    positions.append(pos >= 0)
    zero_positions.append(abs(M2) == 0)
    
    # 2. Грань ABD (противоположная C)
    M1 = mixed_product(AB, AD, AC)
    M2 = mixed_product(AB, AD, AP)
    pos = M1 * M2
    positions.append(pos >= 0)
    zero_positions.append(abs(M2) == 0)
    
    # 3. Грань ACD (противоположная B)
    M1 = mixed_product(AC, AD, AB)
    M2 = mixed_product(AC, AD, AP)
    pos = M1 * M2
    positions.append(pos >= 0)
    zero_positions.append(abs(M2) == 0)
    
    # 4. Грань BCD (противоположная A)
    M1 = mixed_product(BC, BD, BA)  
    M2 = mixed_product(BC, BD, BP)
    pos = M1 * M2
    positions.append(pos >= 0)
    zero_positions.append(abs(M2) == 0)

    # Проверка всех условий
    if not all(positions):
        return 'Снаружи'
    
    zero_count = sum(zero_positions)
    
    # Проверка совпадения с вершинами
    for vertex in [A, B, C, D]:
        if np.allclose(P, vertex):
            return 'Совпадение с вершиной'
    
    if zero_count == 2:
        return 'На ребре'  # На ребре
    elif zero_count == 1:
        return 'На грани'  # На грани
    else:
        return 'Внутри'  # Внутри


A = np.array([0, 0, 0])
B = np.array([2, 0, 0])
C = np.array([0, 2, 0])
D = np.array([0, 0, 2])
    
coordinates = [
    [0.5, 0.5, 0.5],     # внутри
    [1, 0, 0],           # на ребре AB
    [0.5, 0.5, 0],       # на грани ABC
    [0, 0, 0],           # вершина A
    [-1, -1, -1],        # снаружи 
    ]
    
for P in coordinates:
    result = point_position(P, A, B, C, D)
    print(result)

