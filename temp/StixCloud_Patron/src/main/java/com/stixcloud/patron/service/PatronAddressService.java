package com.stixcloud.patron.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stixcloud.common.exception.SisticApiException;
import com.stixcloud.domain.Address;
import com.stixcloud.domain.PatronAddress;
import com.stixcloud.patron.api.json.AddressJson;
import com.stixcloud.patron.api.json.PatronProfileJson;
import com.stixcloud.patron.constant.PatronConstant;
import com.stixcloud.patron.repo.IPatronAddressRepository;

@Service
public class PatronAddressService implements IServiceUpdate {

  private static final Logger logger = LogManager.getLogger(PatronAddressService.class);
  @Autowired
  private IPatronAddressRepository repo;
  @Autowired
  private IAddressService addressService;

  @Override
  public boolean isChanged(PatronProfileJson request, PatronProfileJson original) {
    if (request.getAddresses() != null) {
      return !request.getAddresses().equals(original.getAddresses());
    } else if (original.getAddresses() != null || !original.getAddresses().isEmpty()) {
      return true;
    }
    return false;

  }

  @Override
  public void updatePatron(PatronProfileJson request, Long entityId, Long userInfoId) throws SisticApiException {

    logger.info("Update patron address is in progress.");
    Integer userInfoIdInt = Math.toIntExact(userInfoId);
    List<PatronAddress> patronAddresses = repo.findByPatronProfileId(entityId);
    List<AddressJson> addressJsons = null != request.getAddresses() && !request.getAddresses().isEmpty() ? request.getAddresses() : new ArrayList<AddressJson>();
    List<Long> addressIds = new ArrayList<Long>();
    List<Address> addressList = null;
    // Case update
    if (patronAddresses != null && !patronAddresses.isEmpty()) {
      patronAddresses.forEach(address -> {
        addressIds.add(address.getAddressId());
        Address addressTmp = addressService.findOne(address.getAddressId());
        if (addressTmp != null && addressJsons.isEmpty()) {
          AddressJson addressJson = addressJsons.stream()
              .filter(p -> addressTmp.getAddresstype().equals(p.getType())).findFirst()
              .orElse(null);
          if (addressJson != null) {
            addressTmp.setLineone(addressJson.getAddressLineOne());
            addressTmp.setLinetwo(addressJson.getAddressLineTwo());
            addressTmp.setLinethree(addressJson.getAddressLineThree());
            addressTmp.setPostcode(addressJson.getPostalCode());
            addressTmp.setCountry(addressJson.getCountryCode());
            addressTmp.setUpdateddate(OffsetDateTime.now());
            addressTmp.setUpdatedBy(userInfoIdInt);
            addressService.save(addressTmp);
          }
        }
      });

      addressList = addressService.findByIds(addressIds);
      // Case delete
      List<String> requestPatronAddressType;
      if (addressJsons != null && !addressJsons.isEmpty()) {
        requestPatronAddressType =
            addressJsons.stream().map(AddressJson::getType).collect(Collectors.toList());
      } else {
        requestPatronAddressType = new ArrayList<>();
      }

      List<Address> deleteAddress =
          addressList.stream().filter(p -> !requestPatronAddressType.contains(p.getAddresstype()))
              .collect(Collectors.toList());
      List<Long> deleteAddressId =
          deleteAddress.stream().map(Address::getAddressid).collect(Collectors.toList());
      if (deleteAddressId != null && !deleteAddressId.isEmpty()) {
        List<PatronAddress> deletePatronAddress = repo.findPatronAddress(deleteAddressId);
        if (deleteAddress != null && !deleteAddress.isEmpty()) {
          repo.delete(deletePatronAddress);
          addressService.deleteAddresses(deleteAddress);
        }
      }
    }
    // Case add new
    List<AddressJson> newAddressJson = null;
    if (patronAddresses != null && !patronAddresses.isEmpty()) {

      List<String> patronAddressTypes =
          addressList.stream().map(Address::getAddresstype).collect(Collectors.toList());
      newAddressJson = addressJsons.stream().filter(p -> !patronAddressTypes.contains(p.getType()))
          .collect(Collectors.toList());
    } else {
      newAddressJson = request.getAddresses();
    }
    if (newAddressJson != null && !newAddressJson.isEmpty()) {
      newAddressJson.forEach(p -> {
        Address newAddress = new Address(p.getType(), p.getAddressLineOne(), p.getAddressLineTwo(),
            p.getAddressLineThree(), null, null, p.getCountryCode(), p.getPostalCode(), userInfoIdInt,
            OffsetDateTime.now(), userInfoIdInt, OffsetDateTime.now());
        Address insertedAdd = addressService.save(newAddress);
        PatronAddress insertPatronAddress = new PatronAddress();
        insertPatronAddress.setAddressId(insertedAdd.getAddressid());
        insertPatronAddress.setPatronProfileId(entityId);
        insertPatronAddress.setIsprimary(
            PatronConstant.PATRON_ADDRESS_PRIMARY.getValue().equals(p.getType()) ? "T" : "F");
        repo.save(insertPatronAddress);
      });
    }

    logger.info("Update patron address was successful.");
  }

  public void insertPatronAddress(PatronAddress patron) {
    repo.save(patron);
  }
  
  /**
   * insert patron address
   * @param userInfoId
   * @param patronProfileId
   * @param addresses
   */
  public void insertPatronAddress(int userInfoId, Long patronProfileId, List<AddressJson> addresses){
	  if (addresses != null && !addresses.isEmpty()) {
		  addresses.forEach(p -> {
			  Address address = new Address(p.getType(), p.getAddressLineOne(), p.getAddressLineTwo(), p.getAddressLineThree(), null,
					  null, p.getCountryCode(), p.getPostalCode(), userInfoId, OffsetDateTime.now(), userInfoId,
					  OffsetDateTime.now());
			  Address insertedAddress = addressService.save(address);
			  PatronAddress patronAddress = new PatronAddress(insertedAddress.getAddressid(), patronProfileId,
					  PatronConstant.PATRON_ADDRESS_PRIMARY.getValue().equals(p.getType()) ? "T" : "F");
			  insertPatronAddress(patronAddress);
		  });
		  logger.info("Insert patron address was successful");
	  }
  }

}
