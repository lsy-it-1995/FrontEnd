#include "arithmetic.h"

template <class T>
arithmetic<T>::arithmetic()
{
}

template <class T>
arithmetic<T>::~arithmetic()
{
}


template <class T>
arithmetic<T>::arithmetic(T a, T b){
    this->a = a;
    this->b = b;
}

template <class T>
T arithmetic<T>::add(){
    return a + b;
}

template <class T>
T arithmetic<T>::sub(){
    return a - b;
}