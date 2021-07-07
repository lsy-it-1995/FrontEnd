# create a set
s = set()

#add elements to set
s.add(1)
s.add(2)
s.add(3)

print(s)
s.add(2)
print(s)

s.remove(2)
print(s)

print(f"The set has {len(s)} elements.")