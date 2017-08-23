package com.stixcloud.cart.repo;

import com.stixcloud.domain.Channel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by chongye on 21/12/2016.
 */
public interface ChannelRepository extends CrudRepository<Channel, Long> {
  @Query("SELECT channel "
      + "FROM Channel channel "
      + "JOIN ProfileInfo profile ON profile.profileChannelId = channel.channelid "
      + "WHERE profile.profileinfoid = :profileInfoId")
  Channel getChannelByProfileInfoId(@Param("profileInfoId") Long profileInfoId);
}
