package it.fiv.FIVecafe.observer;

import it.fiv.FIVecafe.entity.Order;
import java.util.List;

public interface OrderObserver {  //observer
    void update(Order orders);
}
