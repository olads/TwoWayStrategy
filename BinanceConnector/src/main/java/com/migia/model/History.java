package com.migia.model;


import lombok.*;

import java.util.List;


@ToString
@Getter
@Setter
public class History {

long time;

double open;

 double high;

 double low;
double close;

 public History() {
 }

 public double getOpen() {
  return open;
 }

 public void setOpen(double open) {
  this.open = open;
 }

 public double getHigh() {
  return high;
 }

 public void setHigh(double high) {
  this.high = high;
 }

 public double getLow() {
  return low;
 }

 public void setLow(double low) {
  this.low = low;
 }

 public double getClose() {
  return close;
 }

 public void setClose(double close) {
  this.close = close;
 }
}














































































