package com.stixcloud.seatmap.service;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static io.github.benas.randombeans.api.EnhancedRandom.randomListOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.domain.VenueDetailComponentsLc;
import com.stixcloud.domain.VenueOverviewComponentsLc;
import com.stixcloud.domain.VenueOverviewComponentsPc;
import com.stixcloud.seatmap.dto.PriceCategoryDetail;
import com.stixcloud.seatmap.dto.SeatModel;
import com.stixcloud.seatmap.imagerenderer.ImageCreator;
import com.stixcloud.seatmap.repo.PriceCategoryDetailRepository;
import com.stixcloud.seatmap.repo.SeatModelRepository;
import com.stixcloud.seatmap.repo.VenueDetailComponentsLcRepository;
import com.stixcloud.seatmap.repo.VenueOverviewComponentsLcRepository;
import com.stixcloud.seatmap.repo.VenueOverviewComponentsPcRepositoryCustom;
import com.stixcloud.seatmap.repo.VenueSectionPcRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by chongye on 16-Sep-16.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ImageCreationServiceTest {
  @Autowired
  ObjectMapper objectMapper;
  @InjectMocks
  ImageCreationService imageCreationService;
  @Mock
  private ImageCreator imgCreator;
  @Mock
  private PriceCategoryDetailRepository priceCategoryDetailRepository;
  @Mock
  private VenueSectionPcRepository venueSectionPcRepository;
  @Mock
  private VenueOverviewComponentsLcRepository venueOverviewComponentsLcRepository;
  @Mock
  private VenueOverviewComponentsPcRepositoryCustom venueOverviewComponentsPcRepository;
  @Mock
  private VenueDetailComponentsLcRepository venueDetailComponentsLcRepository;
  @Mock
  private SeatModelRepository seatModelRepository;

  @Test
  public void getOverviewImage() throws Exception {
    when(venueSectionPcRepository.findPcSectionId(anyLong(), anyListOf(Long.class)))
        .thenReturn(random(Long.class));

    when(venueOverviewComponentsLcRepository.findAll(anyLong()))
        .thenReturn(randomListOf(5, VenueOverviewComponentsLc.class));

    when(venueOverviewComponentsPcRepository.findAll(anyLong()))
        .thenReturn(randomListOf(5, VenueOverviewComponentsPc.class));

    String mockImagePath = random(String.class);
    when(imgCreator.createOverviewImage(anyListOf(PriceCategoryDetail.class),
        anyListOf(VenueOverviewComponentsLc.class), anyListOf(VenueOverviewComponentsPc.class),
        anyLong(), anyBoolean(), anyBoolean(), anyString())).thenReturn(mockImagePath);

    String resultImagePath = imageCreationService
        .getOverviewImage(randomListOf(5, Long.class), random(Long.class), random(Boolean.class),
            random(Boolean.class), random(String.class));

    verify(venueSectionPcRepository).findPcSectionId(anyLong(), anyListOf(Long.class));
    verify(venueOverviewComponentsLcRepository).findAll(anyLong());
    verify(venueOverviewComponentsPcRepository).findAll(anyLong());
    verify(imgCreator)
        .createOverviewImage(anyListOf(PriceCategoryDetail.class),
            anyListOf(VenueOverviewComponentsLc.class), anyListOf(VenueOverviewComponentsPc.class),
            anyLong(), anyBoolean(), anyBoolean(), anyString());
    assertEquals(mockImagePath, resultImagePath);
  }

  @Test
  public void getDetailImages() throws Exception {
    when(venueDetailComponentsLcRepository.retrieveDetailViewComponents(anyLong(), anyLong()))
        .thenReturn(randomListOf(5, VenueDetailComponentsLc.class));

    when(seatModelRepository.getSectionsForBlock(anyLong(), anyLong()))
        .thenReturn(randomListOf(5, SeatModel.class));

    when(priceCategoryDetailRepository.findPriceCategoryDetailByProductId(anyLong()))
        .thenReturn(randomListOf(5, PriceCategoryDetail.class));

    String mockImagePath = random(String.class);
    when(imgCreator.createDetailImage(anyListOf(Long.class), anyListOf(PriceCategoryDetail.class),
        anyListOf(VenueDetailComponentsLc.class), anyListOf(SeatModel.class), anyLong(),
        anyString())).thenReturn(mockImagePath);

    String resultImagePath = imageCreationService
        .getDetailImages(randomListOf(5, Long.class), random(Long.class), random(String.class));

    verify(venueDetailComponentsLcRepository).retrieveDetailViewComponents(anyLong(), anyLong());
    verify(seatModelRepository).getSectionsForBlock(anyLong(), anyLong());
    verify(priceCategoryDetailRepository).findPriceCategoryDetailByProductId(anyLong());
    verify(imgCreator)
        .createDetailImage(anyListOf(Long.class), anyListOf(PriceCategoryDetail.class),
            anyListOf(VenueDetailComponentsLc.class), anyListOf(SeatModel.class), anyLong(),
            anyString());
    assertEquals(mockImagePath, resultImagePath);
  }

}
