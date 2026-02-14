def f(n):
    a=[1,2]
    if n==1:
        return [1]
    elif n==2:
        return a
    else:
        for i in range(n-2):
            a.append(a[i]+a[i+1])
    return a

x=input()[::-1]
s=0
fx=f(len(x))

for i in range(len(x)):
    if x[i]=='1':
        s+=fx[i]
print(s)
