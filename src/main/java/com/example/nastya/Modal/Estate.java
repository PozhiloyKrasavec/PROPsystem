package com.example.nastya.Modal;

public class Estate {
    int id;
    String ownerName;
    double square;
    int roomQnt;
    String address;
    int price;

    public Estate(int id, double square, int roomQnt, String address, int price,String ownerName) {
        this.id = id;
        this.square = square;
        this.roomQnt = roomQnt;
        this.address = address;
        this.price = price;
        this.ownerName = ownerName;
    }

    public double getSquare() {
        return square;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getRoomQnt() {
        return roomQnt;
    }

    public String getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ID: " + id + " Площадь: "  + square + " Кол-во комнат: " + roomQnt + " Адрес: " + address + " Цена: " + price;
    }
}
