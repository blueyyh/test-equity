package com.trafigura.poc.equity.domain;

/**
 * INSERT / UPDATE / CANCEL are actions on a Trade (with same trade id but different version) INSERT will always be 1st version of a Trade, CANCEL will always be last version of Trade.
 * For UPDATE, SecurityCode or Quantity or Buy/Sell can change
 * For CANCEL, any changes in SecurityCode or Quantity or Buy/Sell may change and should be ignored
 */
public enum TradeAction {
    INSERT, UPDATE, CANCEL;
}
