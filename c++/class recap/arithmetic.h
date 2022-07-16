#ifndef ARITHMETIC_H
#define ARITHMETIC_H

#pragma once

template<class T>
class arithmetic{
public:
    arithmetic();
    arithmetic(T a, T b);
    ~arithmetic();
    T add();
    T sub();

private:
    T a;
    T b;
};

#endif