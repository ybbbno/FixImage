# FixImage

A Paper plugin that lets players "wax" item frames with **Honeycomb** to lock them in place, and remove that lock with an **axe** — mirroring the vanilla copper waxing/scraping mechanic.

## Usage

1. Place an item in an item frame.
2. Hold Honeycomb and rotate the frame (right-click it) to fix it in place — it will no longer rotate on right-click.
3. To free it again, hold any axe and right-click the fixed frame.

## Features

- **Fix a frame:** Right-click an item frame (holding an item) with Honeycomb in your main hand while rotating it. The frame becomes fixed — further rotation attempts are cancelled — and one Honeycomb is consumed from your hand.
- **Unfix a frame:** Right-click a fixed item frame with any axe in your main hand to remove the lock.
- **Feedback:** Both actions play the vanilla wax-on/wax-off sounds and spawn the matching particle effects at the frame's location, so it feels consistent with copper waxing.
- **Persistent:** The fixed state is stored on the item frame's `PersistentDataContainer`, so it survives server restarts and chunk reloads, in addition to using Paper's native `ItemFrame#setFixed`.