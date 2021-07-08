class Point():
    def __init__(self, x, y):
        self.x = x
        self.y = y
    

p = Point(5,6)
print(p.x)
print(p.y)


class Flight():
    def __init__(self, capacity):
        self.capacity = capacity
        self.passengers = []

    def add_passenger(self, name):
        if not self.open_seats():
            return False
        self.passengers.append(name)
        return True

    def open_seats(self):
        return self.capacity-len(self.passengers)    

flight = Flight(3)
people=["harry", "ron", "hermione", "ginny"]
for person in people:
    if flight.add_passenger(person):
        print(f"Add {person} to flight succeessfully")
    else:
        print(f"no avaialbels seats for {person}")
