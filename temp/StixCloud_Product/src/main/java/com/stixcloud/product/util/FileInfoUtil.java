package com.stixcloud.product.util;

import com.stixcloud.common.config.multitenant.TenantContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class FileInfoUtil {
  public static final String SAVE_EVENT_FOLDER_NAME = "EventManagement";
  public static final String IMAGE_OVERVIEW = "image_overview";
  public static final String IMAGE_SEAT_OVERVIEW = "image_seat_overview";
  public static final String JPGE_IMAGE_EXTENSION = ".jpg";
  private static final Logger logger = LogManager.getLogger(FileInfoUtil.class);
  private static final String FILE_SEPARATOR = "/";
  private static final String VELOCITY_FOLDER_NAME = "velocity";
  private static final String SESSION_MANAGEMENT = "sessions";
  private static String NETWORK_FILE_SYSTEM_PATH = "STIX";
  private static String SAVE_FOLDER_ROOT = NETWORK_FILE_SYSTEM_PATH;
  private static String PUBLIC_FOLDER_NAME = "public";
  private static String SEATMAP_ROOT_PUBLIC_PATH = "SeatmapMapping";

  /**
   * Gets the file directory.
   * @param path the path
   * @return the file directory
   */
  public static String getFileDirectory(String path, boolean includeRootDirectory) {
    String rootDirectory = "";
    String tenantId = TenantContextHolder.getTenant().getName();
    StringJoiner joiner = new StringJoiner(FILE_SEPARATOR);
        /*UserInfoDetails userInfoDetails = UserInfoProcessUtil.getCurrentUser();
        if (userInfoDetails != null) {
            tenantId = userInfoDetails.getTenantIdentifier();
        } else {
            tenantId = SisticMultiTenantContextUtil.getTenantId();
        }*/
    if (includeRootDirectory) {
      rootDirectory = SAVE_FOLDER_ROOT;
    }
    return joiner.add(rootDirectory).add(tenantId).add(path).toString();
  }

  /**
   * Gets the file directory.
   * @param path the path
   * @param isPublic the is public
   * @return the file directory
   */
  public static String getFileDirectory(String path, boolean includeRootDirectory,
                                        boolean isPublic) {
    if (!isPublic) {
      return getFileDirectory(path, includeRootDirectory);
    } else {
      StringJoiner joiner = new StringJoiner(FILE_SEPARATOR, FILE_SEPARATOR, "");
            /*UserInfoDetails userInfoDetails =  UserInfoProcessUtil.getCurrentUser();;
            String tenantId = userInfoDetails.getTenantIdentifier();*/
      String tenantId = TenantContextHolder.getTenant().getName();
      if (includeRootDirectory) {
        return joiner.add(SAVE_FOLDER_ROOT).add(PUBLIC_FOLDER_NAME).add(tenantId).add(path)
            .toString();
      } else {
        return joiner.add(PUBLIC_FOLDER_NAME).add(tenantId).add(path).toString();
      }
    }
  }

  /**
   * Get the Velocity Template location directory
   */

  public static String getVelocityFileDirectory(String templateName) {
    StringJoiner joiner = new StringJoiner(FILE_SEPARATOR);
    return getFileDirectory(joiner.add(VELOCITY_FOLDER_NAME).add(templateName).toString(), false);
  }

  /**
   * Gets the Object Serialization path for Session maintenance
   */
  public static String getSessionDirectoryPath(String tenantIdentifier, String path) {
    StringJoiner joiner = new StringJoiner(FILE_SEPARATOR);
    return joiner.add(SAVE_FOLDER_ROOT).add(tenantIdentifier).add(SESSION_MANAGEMENT).add(path)
        .toString();
  }

  public static String getSeatmapOverviewFileLocation(Long mappingId, String fileName,
                                                      boolean isPublic)
      throws RuntimeException {
    String subDirectory = "", publicDirectory = "";
    StringJoiner joiner = new StringJoiner(FILE_SEPARATOR);
    joiner.add(SEATMAP_ROOT_PUBLIC_PATH).add(mappingId.toString());
    try {
      if (StringUtils.isNotBlank(fileName)) {
        subDirectory = joiner.add(fileName).toString();
      } else {
        subDirectory = joiner.toString();
      }

      publicDirectory = FileInfoUtil.getFileDirectory(subDirectory, false, isPublic);
    } catch (Exception e) {
      logger.error("Error occurred getSeatmapOverviewFileLocation - MappingId: " + mappingId
          + ", fileName: " + fileName);
      logger.error(e.getMessage(), e);
      throw new RuntimeException(e);
    }

    logger.debug("getSeatmapOverviewFileLocation - MappingId: " + mappingId + ", FileName: "
        + fileName + ", PublicDirectory: " + publicDirectory);
    return publicDirectory;
  }

  public static String generateRelativeFilePath(String internalFilePath) {
    final String HTTP_SEPARATOR = "/";

    if (!FILE_SEPARATOR.equals(HTTP_SEPARATOR)) {
      if (FILE_SEPARATOR.equals("\\")) {
        internalFilePath = internalFilePath.replace('\\', '/');
      } else {
        internalFilePath = internalFilePath.replace(FILE_SEPARATOR, HTTP_SEPARATOR);
      }
    }

    String[] strings = internalFilePath.split(HTTP_SEPARATOR);
    List<String> stringList = new ArrayList<>(Arrays.asList(strings));
    stringList.remove("");
    stringList.remove("STIX");
    return stringList.stream().collect(Collectors.joining("/", "/", ""));
  }
}
