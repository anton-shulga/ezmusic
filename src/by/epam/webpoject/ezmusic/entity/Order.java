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

    public Order() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (isPaid != order.isPaid) return false;
        if (!orderId.equals(order.orderId)) return false;
        if (!userId.equals(order.userId)) return false;
        if (!songList.equals(order.songList)) return false;
        return totalCost.equals(order.totalCost);

    }

    @Override
    public int hashCode() {
        int result = orderId.hashCode();
        result = 31 * result + userId.hashCode();
        result = 31 * result + songList.hashCode();
        result = 31 * result + totalCost.hashCode();
        result = 31 * result + (isPaid ? 1 : 0);
        return result;
    }
}
