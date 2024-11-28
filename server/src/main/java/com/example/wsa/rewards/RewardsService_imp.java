package com.example.wsa.rewards;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of the RewardsService interface.
 */
@Service
@Slf4j
public class RewardsService_imp implements RewardsService {

  private final int FLAT_POINT_AMOUNT = 5;

  private final RewardsRepo rewardsRepo;

  /**
   * Constructs a new RewardsService_imp with the specified RewardsRepo.
   *
   * @param rewardsRepo the repository used to access rewards data
   */
  public RewardsService_imp(RewardsRepo rewardsRepo) {
    this.rewardsRepo = rewardsRepo;
  }

  @Override
  public void update_points(long volunteer_id) {
    log.debug("Entering update_points() for volunteer_id: {}", volunteer_id);
    List<Integer> eventsRegisteredToUser = rewardsRepo.return_registered_events_list(volunteer_id);
    for (int event_id : eventsRegisteredToUser) {
      boolean isAllocationPresent =
              rewardsRepo.isAttemptedAllocationPresent(volunteer_id, event_id);
      boolean isEventExpired = eventExpiryStatus(event_id);

      if (!isAllocationPresent && isEventExpired) {
        rewardsRepo.add_points(volunteer_id, event_id, FLAT_POINT_AMOUNT);
        log.debug("Added points for volunteer_id: {}, event_id: {}", volunteer_id, event_id);
      } else {
        log.debug("Attempted allocation skipped for volunteer_id: {}, event_id: {}.",
                volunteer_id, event_id);
      }
    }
  }

  @Override
  public Integer retrieve_point_total(long volunteer_id) {
    log.debug("Entering retrieve_point_total() for volunteer_id: {}", volunteer_id);
    Integer totalPoints = rewardsRepo.retrieve_point_total(volunteer_id);
    log.debug("Total points for volunteer_id {}: {}", volunteer_id, totalPoints);
    return totalPoints;
  }

  @Override
  public List<String> retrieve_applicable_reward_names(int point_total) {
    log.debug("Entering retrieve_applicable_reward_names() with point_total: {}", point_total);
    List<RewardThreshold> allRewards = rewardsRepo.retrieve_all_reward_items();
    List<String> applicableRewardNames = new ArrayList<>();

    for (RewardThreshold reward : allRewards) {
      if (point_total >= reward.getPointsRequired()) {
        applicableRewardNames.add(reward.getImageName());
        log.debug("Added applicable reward: {}", reward.getImageName());
      }
    }
    return applicableRewardNames;
  }

  //-- Helper Methods --//

  /**
   * Checks if an event has expired based on its ID.
   *
   * @param event_id the ID of the event
   * @return true if the event has expired, false otherwise
   */
  private Boolean eventExpiryStatus(long event_id) {
    LocalDateTime currentDateTime = LocalDateTime.now();
    LocalDateTime expiryDateTime = formatEventExpiry(event_id);
    boolean isExpired = currentDateTime.isAfter(expiryDateTime);
    log.debug("Event expiry status for event_id {}: {}", event_id, isExpired);
    return isExpired;
  }

  /**
   * Formats the expiry date and time of an event into a LocalDateTime object.
   *
   * @param event_id the ID of the event
   * @return the formatted LocalDateTime object
   */
  private LocalDateTime formatEventExpiry(long event_id) {
    try {
      String eventExpiryDate = rewardsRepo.getEventExpiryDate(event_id);
      String eventExpiryTime = rewardsRepo.getEventExpiryTime(event_id);
      DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      String eventExpiryDateTime = eventExpiryDate + " " + eventExpiryTime;
      LocalDateTime expiryDateTime = LocalDateTime.parse(eventExpiryDateTime, formatDateTime);
      log.debug("Formatted event expiry for event_id {}: {}", event_id, expiryDateTime);
      return expiryDateTime;
    } catch (Exception e) {
      log.error("formatEventExpiry(): Exception occurred for event_id {}", event_id, e);
      return null;
    }
  }
}
