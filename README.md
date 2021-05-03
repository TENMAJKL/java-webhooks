# Discord webhooks
Simple classes to send discord webhooks

# Example

```java
DiscordWebhook hook = new DiscordWebhook("your supercool url");
hook.setAuthor("April");

DiscordEmbed embed = new DiscordEmbed();
embed.setTitle("Nice webhook");
embed.setDescription("Its really nice");
embed.addField("Dev", "Majkl", false);
hook.addEmbed(embed);
hook.send();

```