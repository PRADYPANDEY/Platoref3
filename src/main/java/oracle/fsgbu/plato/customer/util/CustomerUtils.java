package oracle.fsgbu.plato.customer.util;

import oracle.fsgbu.plato.customer.api.model.CustomerModel;
import oracle.fsgbu.plato.customer.domain.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class CustomerUtils {

  private CustomerUtils() {}

  public static final String DEFAULT_DATE_FORMAT = "dd-MMM-yyyy";

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomerUtils.class);

  /**
   * Convert string to long.
   * 
   * @param convertString String value
   * @return Long value of string
   */
  public static final Long convertStringToLong(String convertString) {
    return ((convertString == null) || ("".equals(convertString))) ? null : new Long(convertString);
  }

  /**
   * Convert String to Timestamp.
   */
  public static Timestamp convertStringToTimestamp(String strDate) {
    if (strDate == null || strDate.isEmpty() || "null".equalsIgnoreCase(strDate)) {
      return null;
    }
    try {
      return Timestamp.from(OffsetDateTime.parse(strDate).toInstant());
    } catch (DateTimeParseException dt) {
      LOGGER.info("DateTimeParseException >>>" + dt);
      return null;
    }
  }

  public static CustomerModel convertEntityToVO(Customer entity) {
    CustomerModel vo = null;
    if (entity != null) {
      vo = new CustomerModel();
      vo.setId(entity.getId());
      vo.setAge(entity.getAge());
      vo.setFirstName(entity.getFirstName());
      vo.setLastName(entity.getLastName());
      vo.setSex(entity.getSex());
      vo.setPhone(entity.getPhone());
      vo.setEmail(entity.getEmail());
      vo.setCounty(entity.getCounty());
      vo.setCountry(entity.getCountry());
    }
    return vo;
  }

  public static List<CustomerModel> convertEntityListToVOList(List<Customer> entityList) {
    List<CustomerModel> voList = new ArrayList<>();
    entityList.forEach(entity -> voList.add(convertEntityToVO(entity)));
    return voList;
  }

  public static Customer convertVOToEntity(CustomerModel vo) {
    Customer entity = null;
    if (vo != null) {
      entity = new Customer();
      entity.setId(vo.getId());
      entity.setAge(vo.getAge());
      entity.setFirstName(vo.getFirstName());
      entity.setLastName(vo.getLastName());
      entity.setSex(vo.getSex());
      entity.setPhone(vo.getPhone());
      entity.setEmail(vo.getEmail());
      entity.setCounty(vo.getCounty());
      entity.setCountry(vo.getCountry());
    }
    return entity;
  }

}
