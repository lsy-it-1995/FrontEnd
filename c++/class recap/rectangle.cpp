#include "rectangle.h"

rectangle::rectangle(int l, int b){
    this->length = l;
    this->breadth = b;
}
rectangle::~rectangle(){}

int rectangle::area(){
    return length * breadth;
}

int rectangle::perimeter(){
    return 2*(length + breadth);
}

int rectangle::getLength(){
    return length;
}

void rectangle::setLength(int l){
    length = l;
}