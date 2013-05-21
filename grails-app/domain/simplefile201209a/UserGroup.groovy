package simplefile201209a

import org.apache.commons.lang.builder.HashCodeBuilder

class UserGroup implements Serializable {

	User user
	Group group

	boolean equals(other) {
		if (!(other instanceof UserGroup)) {
			return false
		}

		other.user?.id == user?.id &&
			other.group?.id == group?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (user) builder.append(user.id)
		if (group) builder.append(group.id)
		builder.toHashCode()
	}

	static UserGroup get(long userId, long groupId) {
		find 'from UserGroup where user.id=:userId and group.id=:groupId',
			[userId: userId, groupId: groupId]
	}

	static UserGroup create(User user, Group group, boolean flush = false) {
		new UserGroup(user: user, group: group).save(flush: flush, insert: true)
	}

	static boolean remove(User user, Group group, boolean flush = false) {
		UserGroup instance = UserGroup.findByUserAndGroup(user, group)
		if (!instance) {
			return false
		}

		instance.delete(flush: flush)
		true
	}

	static void removeAll(User user) {
		executeUpdate 'DELETE FROM UserGroup WHERE user=:user', [user: user]
	}

	static void removeAll(Group group) {
		executeUpdate 'DELETE FROM UserGroup WHERE group=:group', [group: group]
	}

	static mapping = {
		id composite: ['group', 'user']
		version false
	}
}
