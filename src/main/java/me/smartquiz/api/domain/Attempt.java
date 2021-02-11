package me.smartquiz.api.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Attempt {

	private UUID id;
	private UUID quizId;
	private float score;
	private LocalDateTime createdOn;
	private UUID createdBy;
}
