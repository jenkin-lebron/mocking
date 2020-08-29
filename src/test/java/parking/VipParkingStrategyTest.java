package parking;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static parking.ParkingStrategy.NO_PARKING_LOT;
@RunWith(MockitoJUnitRunner.class)
public class VipParkingStrategyTest {
    @Mock
    CarDao carDao;
    @InjectMocks
    VipParkingStrategy vipParkingStrategy;

	@Test
    public void testPark_givenAVipCarAndAFullParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 4, Write a test case on VipParkingStrategy.park()
	    * With using Mockito spy, verify and doReturn */
      //given
      VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
      Car car = new Car("JenkinA");
      ParkingLot spyParkingLot = spy(new ParkingLot("Jenkin", 10));
      List<ParkingLot> parkingLots = new ArrayList<>();
      parkingLots.add(spyParkingLot);
      Receipt expectReceipt = new Receipt();
      expectReceipt.setCarName("Jenkin");
      expectReceipt.setParkingLotName("Jenkin");
      //when
      when(spyParkingLot.isFull()).thenReturn(true);
      when(vipParkingStrategy.isAllowOverPark(car)).thenReturn(true);
      Receipt resultReceipt = vipParkingStrategy.park(parkingLots,car);
      //then
      verify(vipParkingStrategy, times(1)).isAllowOverPark(car);
      Assert.assertEquals(resultReceipt, expectReceipt);
    }

    @Test
    public void testPark_givenCarIsNotVipAndAFullParkingLog_thenGiveNoSpaceReceipt() {

        /* Exercise 4, Write a test case on VipParkingStrategy.park()
         * With using Mockito spy, verify and doReturn */
        //given
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        Car car = new Car("JenkinA");
        ParkingLot spyParkingLot = spy(new ParkingLot("Jenkin", 10));
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(spyParkingLot);
        Receipt expectReceipt = new Receipt();
        expectReceipt.setCarName("Jenkin");
        expectReceipt.setParkingLotName(NO_PARKING_LOT);
        //when
        when(spyParkingLot.isFull()).thenReturn(true);
        when(vipParkingStrategy.isAllowOverPark(car)).thenReturn(false);
        Receipt resultReceipt = vipParkingStrategy.park(parkingLots,car);
        //then
        verify(vipParkingStrategy, times(1)).isAllowOverPark(car);
        Assert.assertEquals(resultReceipt, expectReceipt);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsVipCar_thenReturnTrue(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        //given
        Car car = new Car("JenkinA");
        when(carDao.isVip(car.getName())).thenReturn(true);
        //when
        boolean result = vipParkingStrategy.isAllowOverPark(car);
        //then
        Assert.assertEquals(result,true);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsVipCar_thenReturnFalse(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsNotVipCar_thenReturnFalse(){
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
    }

    private Car createMockCar(String carName) {
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        return car;
    }
}
