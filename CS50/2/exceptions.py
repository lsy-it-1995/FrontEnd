import sys
try:
    x = int(input("x:" ))
    y = int(input("y: "))
except ValueError:
    print("value error")
    sys.exit(1)

try:
    result = x/y
except ZeroDivisionError:
    print("error: can't divide by 0")
    sys.exit(1)

print(f"{x} / {y} = {result}")