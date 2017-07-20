package com.saanx.configurator.data.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.math.BigDecimal
import javax.persistence.*

/**
 * @author sandjelkovic
 * @date 20.7.17.
 */
@Entity
data class SlotKt(@Id @GeneratedValue val id: Long = 0,
                  val name: String = "",
                  @OneToMany(fetch = FetchType.LAZY, mappedBy = "slot", orphanRemoval = true, cascade = arrayOf(CascadeType.ALL)) val entries: List<SlotEntryKt> = listOf(),
                  val position: Int = 0,
                  @ManyToOne val configuration: ConfigurationKt = ConfigurationKt(),
                  @Version @JsonIgnore val version: Long = 0) {
    fun value(): BigDecimal = entries.first { it.selected }.value
}
