package nl.aartj.GarageApp.product;

import java.util.Comparator;

public class SortProducts implements Comparator<Product> {

    @Override
    public int compare(Product product1, Product product2){
        if(product1.getProductId() < product2.getProductId()){
            return 1;
        }
        return -1;
    }
}
