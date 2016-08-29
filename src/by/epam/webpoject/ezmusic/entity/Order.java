package by.epam.webpoject.ezmusic.entity;

import java.util.ArrayList;

/**
 * Created by Антон on 21.08.2016.
 */
public class Order {
    private Long orderId;
    private Long userId;
    private ArrayList<Song> songList;
    private Double totalCost;
    private boolean isPaid;

    public Order(){}

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ArrayList<Song> getSongList() {
        return songList;
    }

    public void setSongList(ArrayList<Song> songList) {
        this.songList = songList;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }


}
