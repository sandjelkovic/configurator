package com.saanx.configurator.data.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.validator.constraints.URL
import org.springframework.data.rest.core.annotation.RestResource
import java.math.BigDecimal
import javax.persistence.*

/**
 * @author sandjelkovic
 * @date 20.7.17.
 */
@Entity
class SlotEntryKt(@Id @GeneratedValue val id: Long = 0,
                  val name: String = "",
                  val data: String = "",
                  @URL val url: String = "",
                  val value: BigDecimal = BigDecimal.ZERO,
                  val selected: Boolean = false,
                  val position: Int = 0,
                  @JsonIgnore @ManyToOne @RestResource(exported = false) val slot: SlotKt = SlotKt(),
                  @Version @JsonIgnore val version: Long = 0)

