package com.guedes.tharseo.external;

import com.guedes.tharseo.models.Asset;

import java.time.Instant;

public interface TradeMethodsFactory {

    // public Asset getAsset (); ???
  public Instant getOpenDate ();
  public Double getPriceOpen();
  public Instant getCloseDate();
  public Double getPriceClose();
  public Double getPriceTarget();
  public Double getProfit();
  public Double getStopLoss();

}
