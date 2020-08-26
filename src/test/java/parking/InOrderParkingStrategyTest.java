package parking;

import mocking.CustomerDao;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
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
      Receipt resultReceipt = inOrderParkingStrategy.createReceipt(parkingLot,car);

      Assert.assertEquals(resultReceipt,expectReceipt);

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

        Assert.assertEquals(resultReceipt,expectReceipt);
    }

    @Test
    public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt(){

	    /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for no available parking lot */
      InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();

      ParkingLot spyParkingLot = spy(new ParkingLot("Jenkin",10));
      List<ParkingLot> parkingLots = new ArrayList<>();
      parkingLots.add(spyParkingLot);

      Car car = new Car("Lebron");
      Receipt expectReceipt = new Receipt();
      expectReceipt.setCarName("Lebron");
      expectReceipt.setParkingLotName(NO_PARKING_LOT);

      when(spyParkingLot.isFull()).thenReturn(true);
      //doReturn(true).when(spyParkingLot).isFull();
      verify(spyParkingLot,times(1)).isFull();

      Assert.assertEquals(inOrderParkingStrategy.park(parkingLots,car),expectReceipt);

    }

    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */

    }

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */

    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot(){

        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */

    }


}
