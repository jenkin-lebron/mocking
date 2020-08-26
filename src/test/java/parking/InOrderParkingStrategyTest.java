package parking;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static parking.ParkingStrategy.NO_PARKING_LOT;

public class InOrderParkingStrategyTest {


  @Test
  public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

    /* Exercise 1, Write a test case on InOrderParkingStrategy.createReceipt()
     * With using Mockito to mock the input parameter */
    Car car = mock(Car.class);
    when(car.getName()).thenReturn("Lebron");
    ParkingLot parkingLot = mock(ParkingLot.class);
    when(parkingLot.getName()).thenReturn("Jenkin");
    Receipt expectReceipt = new Receipt();
    expectReceipt.setCarName("Lebron");
    expectReceipt.setParkingLotName("Jenkin");

    InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
    Receipt resultReceipt = inOrderParkingStrategy.createReceipt(parkingLot, car);

    Assert.assertEquals(resultReceipt, expectReceipt);

  }

  @Test
  public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {

    /* Exercise 1, Write a test case on InOrderParkingStrategy.createNoSpaceReceipt()
     * With using Mockito to mock the input parameter */
    Car car = mock(Car.class);
    when(car.getName()).thenReturn("Lebron");

    Receipt expectReceipt = new Receipt();
    expectReceipt.setCarName("Lebron");
    expectReceipt.setParkingLotName(NO_PARKING_LOT);

    InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
    Receipt resultReceipt = inOrderParkingStrategy.createNoSpaceReceipt(car);

    Assert.assertEquals(resultReceipt, expectReceipt);
  }

  @Test
  public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt() {

    /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for no available parking lot */
    InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();

    ParkingLot spyParkingLot = spy(new ParkingLot("Jenkin", 10));
    List<ParkingLot> parkingLots = new ArrayList<>();
    parkingLots.add(spyParkingLot);

    Car car = new Car("Lebron");
    Receipt expectReceipt = new Receipt();
    expectReceipt.setCarName("Lebron");
    expectReceipt.setParkingLotName(NO_PARKING_LOT);

    when(spyParkingLot.isFull()).thenReturn(true);
    //doReturn(true).when(spyParkingLot).isFull();
    verify(spyParkingLot, times(1)).isFull();

    Assert.assertEquals(inOrderParkingStrategy.park(parkingLots, car), expectReceipt);

  }

  @Test
  public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt() {

    /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */
    InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();

    ParkingLot spyParkingLot1 = spy(new ParkingLot("Jenkin", 10));
    ParkingLot spyParkingLot2 = spy(new ParkingLot("Keanu", 10));
    List<ParkingLot> parkingLots = new ArrayList<>();
    parkingLots.add(spyParkingLot1);
    parkingLots.add(spyParkingLot2);

    Car car = new Car("Lebron");
    Receipt expectReceipt = new Receipt();
    expectReceipt.setCarName("Lebron");
    expectReceipt.setParkingLotName("Keanu");

    when(spyParkingLot1.isFull()).thenReturn(true);
    verify(spyParkingLot1, times(1)).isFull();
    when(spyParkingLot2.isFull()).thenReturn(false);
    verify(spyParkingLot2, times(1)).isFull();

    Assert.assertEquals(inOrderParkingStrategy.park(parkingLots, car), expectReceipt);
  }

  @Test
  public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt() {

    /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */
    InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();

    ParkingLot spyFullParkingLot = spy(new ParkingLot("Jenkin", 10));
    ParkingLot spyNotFullParkingLot = spy(new ParkingLot("Lebron", 10));
    List<ParkingLot> parkingLots = new ArrayList<>();
    parkingLots.add(spyFullParkingLot);
    parkingLots.add(spyNotFullParkingLot);
    Car car = new Car("Lebron");
    Receipt expectReceipt = new Receipt();
    expectReceipt.setCarName("Lebron");
    expectReceipt.setParkingLotName("Lebron");

    when(spyFullParkingLot.isFull()).thenReturn(true);
    when(spyNotFullParkingLot.isFull()).thenReturn(false);
    //doReturn(true).when(spyParkingLot).isFull();
    verify(spyFullParkingLot, times(1)).isFull();
    verify(spyNotFullParkingLot, times(1)).isFull();

    Assert.assertEquals(inOrderParkingStrategy.park(parkingLots, car), expectReceipt);
  }

  @Test
  public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot() {

    /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */
    InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();

    ParkingLot spyParkingLot1 = spy(new ParkingLot("Jenkin", 10));
    ParkingLot spyParkingLot2 = spy(new ParkingLot("Keanu", 10));
    List<ParkingLot> parkingLots = new ArrayList<>();
    parkingLots.add(spyParkingLot1);
    parkingLots.add(spyParkingLot2);

    Car car = new Car("Lebron");
    Receipt expectReceipt = new Receipt();
    expectReceipt.setCarName("Lebron");
    expectReceipt.setParkingLotName("Jenkin");

    when(spyParkingLot1.isFull()).thenReturn(false);
    verify(spyParkingLot1, times(1)).isFull();
    when(spyParkingLot2.isFull()).thenReturn(false);
    verify(spyParkingLot2, times(1)).isFull();

    Assert.assertEquals(inOrderParkingStrategy.park(parkingLots, car), expectReceipt);

  }
}
