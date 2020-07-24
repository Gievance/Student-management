package com.version1;

import java.io.IOException;

public interface Scanners<T>{
    T scanner() throws InterruptedException, IOException, ClassNotFoundException;
}
