package com.cgi.sandbox;

@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}
