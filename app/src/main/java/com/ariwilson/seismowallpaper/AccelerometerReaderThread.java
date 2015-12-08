package com.ariwilson.seismowallpaper;

public class AccelerometerReaderThread extends Thread {

  private boolean running_ = true;
  private boolean paused_;
  private volatile AccelerometerReader reader_;
  private SeismoViewThread view_;
  private int period_;

  public AccelerometerReaderThread(AccelerometerReader reader,
      SeismoViewThread view, boolean paused, int period) {
    reader_ = reader;
    view_ = view;
    setPaused(paused);
    period_ = period;
  }

  public void setRunning(boolean running) {
    running_ = running;
  }

  public void setPaused(boolean paused) {
    paused_ = paused;
  }

  @Override
  public void run() {
    while (running_) {
      if (!paused_) {
        view_.update(reader_.x, reader_.y, reader_.z);
      }
      try {
        Thread.sleep(period_);
      } catch (Exception e) {
        // Ignore.
      }
    }
  }
  

}
