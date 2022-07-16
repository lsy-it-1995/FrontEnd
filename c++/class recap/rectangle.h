#ifndef RECTANGLE_H
#define RECTANGLE_H

#pragma once

class rectangle{
    private:
        int length;
        int breadth;
    public:
        rectangle(){
            length = 1;
            breadth = 1;
        }
        rectangle(int length, int breadth);
        ~rectangle();
        int area();
        int perimeter();
        int getLength();
        void setLength(int l);
};

#endif