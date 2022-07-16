#include <iostream>
#include "rectangle.cpp"
#include "arithmetic.cpp"

using namespace std;



int main(){
    rectangle r(10,5);
    cout<<r.area()<<endl;

    arithmetic<float> math(10.1, 5);
    cout<<math.add()<<endl;    
}
    